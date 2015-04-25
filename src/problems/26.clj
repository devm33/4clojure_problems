(ns problems.26)

(defn fib [n]
  (if (< n 2)
    (repeat n 1)
    (let
      [prev (fib (dec n))]
      (conj (+ (first prev) (second prev)) (reverse prev)))))
