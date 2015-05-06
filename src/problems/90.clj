(ns problems.90)

; cartesian product

(def __
  (fn [a b]
    (reduce
      (fn [s ai]
        (apply conj s (map #(vector ai %) b)))
      #{} a))
  )


(assert (= (__ #{1 2 3} #{4 5})
   #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]}))

(assert (= 300 (count (__ (into #{} (range 10))
                  (into #{} (range 30))))))
