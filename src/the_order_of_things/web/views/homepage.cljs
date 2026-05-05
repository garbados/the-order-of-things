(ns the-order-of-things.web.views.homepage 
  (:require
   [the-order-of-things.web.alchemy :as alchemy]
   [the-order-of-things.web.templates.cards :refer [search-cards]]
   [the-order-of-things.web.templates.prompts :as prompts]))

(defn search [-search on-change]
  [:section
   (prompts/search -search
                   :on-change on-change
                   :placeholder "Search poems")
   [:small "Search poems by card name or text content."]])

(defn homepage []
  (let [-search (atom "")
        on-change #(alchemy/refresh :cards (search-cards @-search))]
    [(search -search on-change)
     [:div#cards
      (search-cards @-search)]]))
