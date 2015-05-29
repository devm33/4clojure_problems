(ns problems.127)

; if n consecutive then
(comment
  (quot (* n (inc n)) 2))

; if a is the nth consecutive then
(comment
  (let [nmask (dec (bit-shift-left 1 n))] (= nmask (bit-and nmask a))))

; if a has 1's between h & l (high and low)
(comment
  ((fn [a h l]
     (let [msk (bit-shift-left (dec (bit-shift-left 1 (- (inc h) l))) l)]
       (println h l (Integer/toBinaryString a) (Integer/toBinaryString msk))
       (= msk (bit-and msk a))))
   8 3 3))

(def __
  (letfn [(sumn [n] (quot (* n (inc n)) 2))
          (range? [a h l]
            (let [msk (bit-shift-left (dec (bit-shift-left 1 (- (inc h) l))) l)]
              (= msk (bit-and msk a))))
          (consec ([s] (consec s 0))
            ([s n] (if (empty? s) n
                     (let [nm (dec (bit-shift-left 1 (inc n)))]
                       (if (not= nm (bit-and nm (first s))) n
                         (recur (rest s) (inc n)))))))]
    (fn [s] (sumn (max (consec s) (consec (reverse s)))))))
;;
;;
;; (comment
;;   (assert (= 10 (__ [15 15 15 15 15])))
;;   (assert (= 15 (__ [1 3 7 15 31])))
;;   (assert (= 15 (__ [1 3 7 15 31])))
;;   (assert (= 4 (__ [7 3]))))
;; ; 111      ***
;; ; 011  ->  0*1
