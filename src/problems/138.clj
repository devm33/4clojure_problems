(ns problems.138)

(def __
  (let [dirs (cycle [[1 1] [-1 1] [-1 -1] [1 -1]])]
    (fn [a b]
      (let [xs (mapcat str (take-while #(<= % b) (iterate #(* % %) a)))
            r (int (Math/ceil (Math/sqrt (count xs))))
            w (dec (* 2 r)) h (quot w 2)]
        (->>
          (loop [s (concat xs (repeat (- (* r r) (count xs)) "*"))
                 o (vec (repeat w (vec (repeat w " "))))
                 p (if (even? r) [h 0] [h (dec w)])
                 d (if (even? r) dirs (nnext dirs))]
            (if (empty? s) o
              (let [nextp (map + (first d) p)
                    turn? (not= " " (get-in o nextp))]
                (recur (butlast s)
                       (assoc-in o p (last s))
                       (if turn? (map + p (second d)) nextp)
                       (if turn? (next d) d)))))
          (map (partial apply str)))))))

(__ 2 256)
