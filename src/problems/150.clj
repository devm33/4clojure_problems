(ns problems.150)

(def __
  (letfn [(numseq [n] (map #(read-string (str %)) (str n)))
          (seqnum [s] (read-string (apply str s)))
          (palin? [s] (if (empty? s) true
                        (when (= (first s) (last s))
                          (recur (butlast (rest s))))))
          (fsthalf [s] (take (quot (inc (count s)) 2) s))
          (mirror-even [n] (read-string
                             (str n (clojure.string/reverse (str n)))))
          (mirror-odd [n] (read-string
                            (str n (when (> n 10) (clojure.string/reverse
                                                    (str (quot n 10)))))))
          (nextpal [n] (let [nseq (numseq n)
                             finc (inc (seqnum (fsthalf nseq)))
                             npal (if (even? (count nseq))
                                    (mirror-even finc) (mirror-odd finc))]
                         (println n finc npal)
                         (lazy-seq (cons npal (nextpal npal)))))]
    (fn [start]
      (if (palin? (numseq start))
        (lazy-seq (cons start (nextpal start)))
        (recur (inc start))))))

;; (assert (= (take 6 (__ 1234550000))
;;            [1234554321 1234664321 1234774321
;;            1234884321 1234994321 1235005321]))

;; (= (take 26 (__ 0))
;;    [0 1 2 3 4 5 6 7 8 9
;;     11 22 33 44 55 66 77 88 99
;;     101 111 121 131 141 151 161])

(take 26 (__ 0))
