(ns problems.120)


(defn __ [s]
  (count
    (filter
      (fn [n]
        (< n (reduce + (map #(* % %)
                            (map #(mod % 10)
                                 (take-while
                                   (partial < 0)
                                   (iterate #(quot % 10) n))))))) s)))

(__ (range 10))
