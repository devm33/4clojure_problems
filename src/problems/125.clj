(ns problems.125)

; clojure quine

(def __

  (fn [] (let [a "(fn [] (let [a %s] (format a (pr-str a))))"] (format a (pr-str a))))

  )

(println (__))

; even shorter solution

(fn [x] (str x x)) (quote (fn [x] (str x x)))
