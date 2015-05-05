(ns problems.82)

(comment
  (defn test-neighbors? [a b]
    ; returns true if the two words differ by only one insertion, substition,
    ; or deletion, false otherwise
    (if (= (count a) (count b))
      (= 1 (count (filter not (map = a b))))
      (loop [as (seq a)
             bs (seq b)
             d 0]
        (let [feq (= (first as) (first bs))]
          (if (and (empty? as) (empty? bs))
            (< d 2)
            (if (and (> 0 d) (not feq))
              false
              (let [ashort (< (count as) (count bs))]
                (recur
                  (if (or feq (not ashort)) (rest as) as)
                  (if (or feq ashort) (rest bs) bs)
                  (if feq d (inc d)
                    ))))))))))


(defn all-neighbors? [words]
  (letfn [(neighbors? [a b]
            (if (= (count a) (count b))
              (= 1 (count (filter not (map = a b))))
              (loop [as (seq a)
                     bs (seq b)
                     d 0]
                (let [feq (= (first as) (first bs))]
                  (if (and (empty? as) (empty? bs))
                    (< d 2)
                    (if (and (> 0 d) (not feq))
                      false
                      (let [ashort (< (count as) (count bs))]
                        (recur
                          (if (or feq (not ashort)) (rest as) as)
                          (if (or feq ashort) (rest bs) bs)
                          (if feq d (inc d))))))))))]
    (letfn [(find-neighbor [n xs] (some #(when (neighbors? % n) %) xs))]
      (let [fw (first words)]
        (loop [fst fw lst fw xs (disj words fw)]
          (if (empty? xs)
            true
            (let [nfst (find-neighbor fst xs)]
              (let [nlst (find-neighbor lst (if nfst (disj xs nfst) xs))]
                (if (not (or nfst nlst))
                  false
                  (let [rf (or nfst fst) rl (or nlst lst)]
                    (recur rf rl (disj xs rf rl))))))))))))


(def __ all-neighbors?)


(assert (= true (__ #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"})))
(assert (= false (__ #{"cot" "hot" "bat" "fat"})))
(assert (= false (__ #{"to" "top" "stop" "tops" "toss"})))
(assert (= true (__ #{"spout" "do" "pot" "pout" "spot" "dot"})))
(assert (= true (__ #{"share" "hares" "shares" "hare" "are"})))
(assert (= false (__ #{"share" "hares" "hare" "are"})))

(= true (__ #{"coat" "hat" "dog" "cat" "oat" "cot" "hot" "hog"}))
