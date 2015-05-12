(ns problems.121)

; no warning, but can't use eval

(defn __ [sexp]
  (fn [vars]
    (eval (map #(get vars % %) sexp))))

((__ '(/ a b)) '{a 16 b 8})


; see repl example here maybe https://clojuredocs.org/clojure.core/-%3E
