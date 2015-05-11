(ns problems.121)

(defn __ [sexp vars]
  )


(eval '(+ 2 2))


(defn letlit [sexp vars]
  (let [{:syms (keys vars)} vars]
    (eval sexp)))

(letlit '(+ x 1) '{x 1})

; see repl example here maybe https://clojuredocs.org/clojure.core/-%3E
