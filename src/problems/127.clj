(ns problems.127)

; if n consecutive then
(comment
  (quot (* n (inc n)) 2))

; if a is the nth consecutive then
(comment
  (let [nmask (dec (bit-shift-left 1 n))] (= nmask (bit-and nmask a))))

; if a has 1's between h & l (high and low)
(defn has-range? [a h l]
  (when (and (<= 0 l) (not= h l))
    (let [msk (bit-shift-left (dec (bit-shift-left 1 (- h l))) l)]
      ;; (println h l (Integer/toBinaryString a) (Integer/toBinaryString msk))
      (= msk (bit-and msk a)))))

(assert (has-range? 8 4 3))
(assert (not (has-range? 8 3 2)))

; return the total size of a triangle with base at r [h l] decreasing in size
(defn check-dec [rock s r [h l] d]
  (if (or (>= r (count rock)) (not (has-range? (nth rock r) h l))) 0
    (if (= 1 (- h l)) (inc s)
      (recur rock (+ s (- h l)) (inc r) (map + [h l] d) d))))

; return size of largest triangle starting at [r c] in rock going down
(defn tri-at
  ([rock r c]
   (->>
     [[1 0] [0 -1] [1 -1]]
     (map #(tri-at rock 0 r [(inc c) c] %))
     (apply max)))
  ([rock s r [h l] d]
   (if (or (>= r (count rock)) (not (has-range? (nth rock r) h l)))
     (if (= d [1 -1]) s
       (let [nd (map #(* -1 %) d)]
         (max s (check-dec rock s r (map + nd nd [h l]) nd))))
     (recur rock (+ s (- h l)) (inc r) (map + d [h l]) d))))

(assert (= 9 (tri-at [1 3 7 3 1] 0 0)))

(def __
  (letfn [(has-range? [a h l]
            (when (and (<= 0 l) (> h l))
              (let [msk (bit-shift-left (dec (bit-shift-left 1 (- h l))) l)]
                (= msk (bit-and msk a)))))
          (check-dec [rock s r [h l] d]
            (if (or (>= r (count rock)) (not (has-range? (nth rock r) h l))) 0
              (if (= 1 (- h l)) (inc s)
                (recur rock (+ s (- h l)) (inc r) (map + [h l] d) d))))
          (tri-at
            ([rock r c]
             (->> [[1 0] [0 -1] [1 -1]]
                  (map #(tri-at rock 0 r [(inc c) c] %))
                  (apply max)))
            ([rock s r [h l] d]
             (if (or (>= r (count rock)) (not (has-range? (nth rock r) h l)))
               (if (= d [1 -1]) s
                 (let [nd (map #(* -1 %) d)]
                   (max s (check-dec rock s r (map + nd nd [h l]) nd))))
               (recur rock (+ s (- h l)) (inc r) (map + d [h l]) d))))
          (log2 [n] (loop [q n l 0] (if (< q 2) l (recur (quot q 2) (inc l)))))
          (best-tri [rock]
            (apply max (for [r (range (dec (count rock)))
                             c (range (inc (log2 (nth rock r))))]
                         (tri-at rock r c))))]
    (fn [s]
      (let [r (max (best-tri s) (best-tri (reverse s)))] (when (> r 1) r)))))


(assert (= 10 (__ [15 15 15 15 15])))
(assert (= 15 (__ [1 3 7 15 31])))
(assert (= 15 (__ [1 3 7 15 31])))
(assert (= 4 (__ [7 3])))
(assert (= 6 (__ [17 22 6 14 22])))
(assert (= 9 (__ [18 7 14 14 6 3])))
(assert (= nil (__ [21 10 21 10])))
(assert (= nil (__ [0 31 0 31 0])))
