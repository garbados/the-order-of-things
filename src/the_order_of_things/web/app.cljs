(ns the-order-of-things.web.app
  (:require
   [clojure.string :as string]
   [shadow.cljs.modern :refer (defclass)]
   [the-order-of-things.web.alchemy :as alchemy]
   [the-order-of-things.web.routing :as routing]
   [the-order-of-things.web.templates.about :refer [about]]
   [the-order-of-things.web.templates.layout :as layout]
   [the-order-of-things.web.views.homepage :refer [homepage]]
   [the-order-of-things.web.views.spread :refer [poem-spread]]))

(def route->view
  {:index homepage
   :about about
   :spread poem-spread})

(def refresh-hash (partial routing/refresh-hash route->view "main" :index))

(defn main-view [node]
  (.appendChild node (alchemy/alchemize layout/container))
  (js/window.addEventListener "popstate" refresh-hash)
  (refresh-hash))

(defclass MainComponent
  (extends js/HTMLElement)
  (constructor [this] (super))
  Object
  (connectedCallback [this] (main-view this)))

(def components
  {:main-component MainComponent})

(def already-defined? "has already been defined as a custom element")
(defn- start-app []
  (try
    (doseq [[component-kw component] components]
      (js/customElements.define (name component-kw) component))
    (catch js/Object e
      ;; redefining custom elements is impossible
      ;; so if webcomponents complains about dev trying to do so, reload
      ;; but otherwise, just print the error
      (if (string/ends-with? (ex-message e) already-defined?)
        (js/window.location.reload)
        (js/console.log e)))))

(start-app)
