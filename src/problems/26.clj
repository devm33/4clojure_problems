(ns problems.26)

(defn fib [n]
  (if (< n 2)
    (repeat n 1)
    (let
      [prev (fib (dec n))]
      (concat prev (list (+ (first prev) (second prev)))))))

(fib 2)
