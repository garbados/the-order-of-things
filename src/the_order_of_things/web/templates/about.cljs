(ns the-order-of-things.web.templates.about 
  (:require
   [the-order-of-things.text :as text]
   [clojure.string :as string]))

(def about
  (let [lines (string/split-lines text/about)]
    [:article
     [:h3 (subs (first lines) 1)]
     [:p
      (for [line (drop 2 lines)]
        (if (seq line)
          [:span line [:br]]
          [:br]))]]))
