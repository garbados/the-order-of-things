(ns the-order-of-things.web.views.homepage 
  (:require
   [the-order-of-things.web.alchemy :as alchemy]
   [the-order-of-things.web.templates.cards :refer [list-cards]]
   [the-order-of-things.web.templates.prompts :as prompts]))

(defn search [-search on-submit]
  [:div.field
   [:div.control.has-icons-right
    (prompts/text -search
                  :on-change on-submit
                  :placeholder "🔍 Search poems")]])

(defn homepage []
  (let [-search (atom "")
        on-submit #(alchemy/refresh :cards (list-cards @-search))]
    [(search -search on-submit)
     [:div#cards
      (list-cards @-search)]]))
