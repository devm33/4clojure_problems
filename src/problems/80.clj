(ns problems.80)

(defn __ [n] (= n (apply + (filter #(= 0 (mod n %)) (range 1 n)))))


(__ 6)
