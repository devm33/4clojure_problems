(ns problems.178)

(defn __ [cardstrs]
  (let
    [get-card
     (fn [[s r]]
       {:suit (get {\D :diamond \H :heart \C :club \S :spade} s)
        :rank (if-let [R (get {\A 12 \K 11 \Q 10 \J 9 \T 8} r)] R
                (- (Character/getNumericValue r) 2))})
     cards (map get-card cardstrs)
     rank-counts (sort (map count (vals (group-by :rank cards))))
     flush? (apply = (map :suit cards))
     has-straight #(every? true? (map = % (iterate inc (first %))))
     ranks (sort (map :rank cards))
     ranks-acelow (sort (replace {12 -1} ranks))
     straight? (some has-straight (list ranks ranks-acelow))]
    (if (and straight? flush?) :straight-flush
      (if (= (last rank-counts) 4) :four-of-a-kind
        (if (= [2 3] rank-counts) :full-house
          (if flush? :flush
            (if straight? :straight
              (if (= (last rank-counts) 3) :three-of-a-kind
                (if (= [1 2 2] rank-counts) :two-pair
                  (if (= (last rank-counts) 2) :pair
                    :high-card))))))))))

; note this would have been cleaner with cond instead of nested-ifs


(assert (= :high-card (__ ["HA" "D2" "H3" "C9" "DJ"])))

(assert (= :pair (__ ["HA" "HQ" "SJ" "DA" "HT"])))

(assert (= :two-pair (__ ["HA" "DA" "HQ" "SQ" "HT"])))

(assert (= :three-of-a-kind (__ ["HA" "DA" "CA" "HJ" "HT"])))

(assert (= :straight (__ ["HA" "DK" "HQ" "HJ" "HT"])))

(assert (= :straight (__ ["HA" "H2" "S3" "D4" "C5"])))

(assert (= :flush (__ ["HA" "HK" "H2" "H4" "HT"])))

(assert (= :full-house (__ ["HA" "DA" "CA" "HJ" "DJ"])))

(assert (= :four-of-a-kind (__ ["HA" "DA" "CA" "SA" "DJ"])))

(assert (= :straight-flush (__ ["HA" "HK" "HQ" "HJ" "HT"])))
