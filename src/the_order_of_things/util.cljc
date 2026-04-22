(ns the-order-of-things.util
  (:require
   [clojure.string :as string]))

(defn md-ish
  "Who needs Marked when you can do this?"
  [s]
  (when s
    (as-> s $
      (reduce
       (fn [s [form tag]]
         (string/replace s
                         (re-pattern (str form "(.+?)" form))
                         (str "<" tag ">" "$1" "</" tag ">")))
       $
       [["\\*\\*" "strong"]
        ["\\*" "em"]])
      (string/replace $ #"- (.+)\n?" "<li>$1</li>")
      (string/replace $ #"<li>(.+)</li>" "<ul><li>$1</li></ul>")
      (string/replace $ #"(.+?)  \n" "$1<br>")
      (string/replace $ #"(.+?)\n{2}" "<p>$1</p>")
      (string/replace $ #"\[(.+?)\]\((.+?)\)" "<a href=\"$2\">$1</a>"))))
