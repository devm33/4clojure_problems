(ns problems.116)

(defn __ [n]
  (letfn [(sieve [n]
            (loop [s (set (range 2 n)) i 2]
              (if (>= i n) s
                (recur (apply disj s (range (+ i i) n i)) (inc i)))))]
    (boolean
      (let [p (sieve (* 2 n))]
        (when (contains? p n)
          (let [ps (sort p) ni (.indexOf ps n)]
            (when (< 0 ni (count ps))
              (= (* 2 n) (+ (nth ps (inc ni)) (nth ps (dec ni)))))))))))


(assert (= false (__ 4)))
(assert (= true (__ 563)))
(assert (= 1103 (nth (filter __ (range)) 15)))
