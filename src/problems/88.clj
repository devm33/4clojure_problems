(ns problems.88)

; symmetric difference between sets (xor)

(defn __ [a b]
  (apply disj (clojure.set/union a b) (clojure.set/intersection a b)))


(assert (= (__ #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7}))
(assert (= (__ #{:a :b :c} #{}) #{:a :b :c}))
(assert (= (__ #{} #{4 5 6}) #{4 5 6}))
(assert (= (__ #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]}))
