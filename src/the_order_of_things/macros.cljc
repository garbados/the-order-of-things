(ns the-order-of-things.macros)

(defmacro inline-slurp [path]
  #_{:clj-kondo/ignore [:unresolved-var]}
  (clojure.core/slurp (eval path)))
