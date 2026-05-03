(ns the-order-of-things.web.templates.cards 
  (:require
   [clojure.string :as string]
   [the-order-of-things.text :as text]))

(defn list-cards
  ([] (list-cards ""))
  ([search-string]
   (for [card-poem text/content
         :let [{card-name :title
                poem-lines :lines
                {media-description :description :keys [src]} :media}
               card-poem]
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
     [:div.block>div.box
      [:div.columns
       (when (seq src)
         [:div.column.is-3
          [:figure.image
           [:img {:alt media-description
                  :title media-description
                  :src src}]]])
       [:div.column
        [:h3 card-name]
        (when (seq poem-lines)
          [:blockquote>p
           (for [line poem-lines]
             [:span line [:br]])])]]])))
