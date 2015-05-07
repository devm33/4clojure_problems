(ns problems.99)

(defn digits [n]
  (reverse (map #(mod % 10) (take-while (partial < 0) (iterate #(quot % 10) n)))))

(digits 1234560)
