(ns the-order-of-things.text
  (:require
   [clojure.edn :as edn]
   [clojure.string :as string]
   [the-order-of-things.macros :include-macros true :refer [inline-slurp]]))

(defn ->kw [s]
  (-> s
      string/trim
      string/lower-case
      (string/replace #"\s+" "-")
      keyword))

(def about (inline-slurp "doc/about.md"))
(def poems (inline-slurp "doc/the_order_of_things.md"))

(def content
  (as-> poems $
    (string/split $ #"\#+\s+?")
    (filter not-empty $)
    (map #(string/split % #"\n\n") $)
    (map
     (fn [[title body]]
       (let [id (->kw title)]
         (cond-> {:title title :id id}
           body (assoc :lines (string/split body #"\n")))))
     $)
    (drop 1 $)))
