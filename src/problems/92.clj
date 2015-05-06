(ns problems.92)

; roman numerals to decimal

(defn __ [roman]
  (loop [v 0
         s (->> (seq roman)
                (map {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000})
                (partition-by identity)
                (map (partial apply +)))]
    (if (< (count s) 2)
      (apply + v s)
      (let [s0 (first s) s1 (second s)]
        (recur
          (if (< s0 s1) v (+ v s0))
          (cons (if (< s0 s1) (- s1 s0) s1) (drop 2 s)))))))


(assert (= 14 (__ "XIV")))
(assert (= 827 (__ "DCCCXXVII")))
(assert (= 3999 (__ "MMMCMXCIX")))
(assert (= 48 (__ "XLVIII")))
