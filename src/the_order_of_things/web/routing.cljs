(ns the-order-of-things.web.routing
  (:require
   [clojure.string :as string]
   [the-order-of-things.web.alchemy :as alchemy]))

(defn refresh-hash [route->view main-id default-route]
  (let [[url-hash query-fragment] (string/split (str js/document.location.hash) #"\?")
        search-params
        (->> (string/split query-fragment #"\&")
             (map #(string/split % #"="))
             (map (fn [[k v]] [(keyword k) v]))
             (map (fn [[k v]] [k (let [maybe-v (js/parseInt v)]
                                   (if (js/isNaN maybe-v) v maybe-v))]))
             (reduce (fn [acc [k v]] (assoc acc k v)) {}))
        [_ path-fragment] (re-matches #"#/(.+)$" url-hash)
        route (keyword path-fragment)
        view (or (route->view route)
                 (route->view default-route))]
    (alchemy/refresh main-id
                     (if (fn? view)
                       (if (seq search-params)
                         (view search-params)
                         (view))
                       view))))
