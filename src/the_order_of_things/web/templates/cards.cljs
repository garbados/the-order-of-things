(ns the-order-of-things.web.templates.cards 
  (:require
   [clojure.string :as string]
   [the-order-of-things.text :as text]))

(defn describe-card
  [{poem-id :id
    card-name :title
    poem-lines :lines}]
  [:section>article.grid
   {:style "grid-template-columns: 25% 1fr;"}
   [:img {:alt card-name
          :title card-name
          :src (str "img/smith-waite/" (name poem-id) ".jpg")}]
   [:div
    [:h3 card-name]
    (when (seq poem-lines)
      [:p
       (for [line poem-lines]
         [:span line [:br]])])]])

(defn search-cards
  ([] (search-cards ""))
  ([search-string]
   (for [card-poem text/content
         :let [{card-name :title poem-lines :lines} card-poem]
         :when
         (or
          (empty? search-string)
          (string/includes? (string/lower-case card-name)
                            (string/lower-case search-string))
          (not-empty
           (filter
            #(string/includes? (string/lower-case %)
                               (string/lower-case search-string))
            poem-lines)))]
     (describe-card card-poem))))
