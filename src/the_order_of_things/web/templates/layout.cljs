(ns the-order-of-things.web.templates.layout)

(def title
  [:hgroup
   {:style "text-align: center;"}
   [:h2 "🍃 The Order of Things 👑"]
   [:p "An epic poem in 78 parts"]])

(def navbar
  [:nav
   {:role "navigation" :aria-label "main navigation"
    :style "text-align: center;"}
   [:ul
    [:li>a {:href "#/"} "🛖 Home"]
    [:li>a {:href "#/spread"} "🃏 Spread"]]
   [:ul
    [:li>a {:href "#/about"} "About ❓"]
    [:li>a {:href "//github.com/garbados/the-order-of-things"  :target "_blank"} "Source 👩‍💻"]]])

(def container
  [:main.container
   [:section title navbar]
   [:section#main
    [:article {:aria-busy "true"} "Loading..."]]])
