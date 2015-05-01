(ns problems.66)

(defn __ [a b]
  (if (= b 0)
    a
    (__ b (mod a b))))

(assert (= (__ 2 4) 2))
(assert (= (__ 10 5) 5))
(assert (= (__ 5 7) 1))
(assert (= (__ 1023 858) 33))
