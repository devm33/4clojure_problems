(ns problems.86)

; happy little numbers =)

(defn __ [h]
  (loop [seen #{} cur h]
    (if (= 1 cur) true
      (if (contains? seen cur) false
        (recur
          (conj seen cur)
          (reduce + (map #(* % %) (loop [n cur d []]
                                    (if (zero? n) d
                                      (recur (quot n 10) (conj d (mod n 10))))))))))))


(assert (= (__ 7) true))
(assert (= (__ 986543210) true))
(assert (= (__ 2) false))
(assert (= (__ 3) false))
