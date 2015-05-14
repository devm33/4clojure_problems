(ns problems.125)

; clojure quine

(def __

  (fn [] (let [a "(fn [] (let [a "b"] (clojure.string/join \" [a a \b b b])))"]
           (clojure.string/join \" [a a \b b b])))

  )


(println (__))



