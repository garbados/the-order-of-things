(defproject the-order-of-things "0.1.0-SNAPSHOT"
  :description "The Order of Things: a 78-poem series, based on Tarot and the Epic of Gilgamesh."
  :url "https://github.com/garbados/the-order-of-things"
  :license {:name "CC BY-NC-ND"
            :url "https://creativecommons.org/licenses/by-nc-nd/4.0/"}
  :dependencies [[org.clojure/clojure "1.12.2"]]
  :repl-options {:init-ns the-order-of-things.core}
  :profiles
  {:cljs
   {:source-paths ["src" "test"]
    :dependencies [[thheller/shadow-cljs "3.2.0"]]}})
