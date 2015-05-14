(ns problems.125)

; clojure quine

(def __

  (fn (let [a "(fn (let [a " b ""] (str a \" a "b \"" b b)))

  )


(println (str '__))

(= (str '__) (__))

