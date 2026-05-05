(ns the-order-of-things.web.views.spread 
  (:require
   [the-order-of-things.text :as text]
   [the-order-of-things.web.alchemy :as alchemy]
   [the-order-of-things.web.templates.cards :refer [describe-card]]
   [the-order-of-things.web.templates.prompts :as prompts]))

(defn number-input [-n on-change]
  [:section
   (prompts/number -n
                   :on-change on-change
                   :placeholder "🔢 Draw N poems"
                   :props {:max 78
                           :min 1})
   [:small "Enter the number of cards to include in the spread."]])

(defn poem-spread
  ([] (poem-spread {}))
  ([{:keys [n] :or {n 3}}]
   (let [-n (atom 3)
         on-change #(alchemy/refresh :cards
                                     (for [card-poem (take (js/parseInt @-n) (shuffle text/content))]
                                       (describe-card card-poem)))]
     [(number-input -n on-change)
      [:div#cards
       (for [card-poem (take n (shuffle text/content))]
         (describe-card card-poem))]])))
