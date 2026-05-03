(ns the-order-of-things.web.templates.layout)

(def navbar
  [:nav.navbar
   {:role "navigation" :aria-label "main navigation"}
   [:div.navbar-brand
    [:a.navbar-item
     {:href "#/"}
     [:h1.title "🍃 The Order of Things 👑"]]]
   [:div.navbar-menu
    [:div.navbar-end
     [:div.navbar-item
      [:a.button.is-info.is-light
       {:href "https://github.com/garbados/the-order-of-things"
        :target "_blank"}
       "Source 👩‍💻"]]]]])

(def container
  [:div.container
   navbar
   [:div.columns.is-desktop
    [:div.column
     [:div.box
      [:div.content#main
       [:h1.title "Loading..."]]]]]])
