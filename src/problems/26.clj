(ns problems.26)

(defn fib [n]
    (if (< n 3)
      (repeat n 1)
      (let
        [prev (reverse (fib (dec n)))]
        (reverse (conj prev (+ (first prev) (second prev)))))))

(fib  5)