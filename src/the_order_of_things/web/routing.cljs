(ns the-order-of-things.web.routing
  #_(:require
     [clojure.string :as string]))

#_(defn route->hash [kw]
  (str "#/" (name kw)))

#_(defn route->href [route & args]
  {:href (string/join "/" (cons (route->hash route) args))})

#_(defn goto-str [s]
  (set! js/window.location s))

#_(defn goto-search [route->hash query]
  (goto-str (str (route->hash :search) "/" query)))

#_(defn goto [route->hash not-found-route route & args]
  (let [route (route->hash route (route->hash not-found-route))]
    (goto-str (string/join "/" (cons route args)))))

#_(defn redirect
  ([route->hash default-route] (redirect route->hash  default-route))
  ([route->hash default-route route] (redirect route->hash default-route route 1))
  ([route->hash default-route route wait-ms]
   (js/setTimeout #(goto route->hash default-route route) wait-ms)
   [:p "Redirecting..."]))

#_(defn find-view [route->view url-hash]
  (->> (keys route->view)
       (filter #(re-find (re-pattern %) url-hash))
       sort
       last
       route->view))

#_(defn scroll-to-top []
  (js/window.scrollTo (clj->js {:left 0
                                :top 0
                                :behavior "smooth"})))

#_(defn refresh-factory [route->view main-id not-found-route default-route]
  (fn []
    (let [url-hash js/document.location.hash]
      (if (seq url-hash)
        (if-let [view (find-view route->view url-hash)]
          (do
            (scroll-to-top)
            (view (alchemy/snag main-id)))
          (goto-str (string/replace-first url-hash #"^#/" (route->hash not-found-route))))
        (goto route->view not-found-route default-route)))))
