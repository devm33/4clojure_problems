(ns problems.26)

(defn fib [n]
    (if (< n 3)
      (repeat n 1)
      (let
        [prev (reverse (fib (dec n)))]
        (reverse (conj prev (+ (first prev) (second prev)))))))

(fib  5)

(defn fibo [n]
  (letfn [(fib [a b] (cons a (lazy-seq (fib b (+ a b)))))]
    (take n (fib 1 1))))

(fibo 7)

(defn fibloop [n]
  (loop [remain n ret [] a 0 b 1]
    (if (= 0 remain) ret
      (recur (dec remain)
             (conj ret a)
             b
             (+ a b)))))

(fibloop 7)
