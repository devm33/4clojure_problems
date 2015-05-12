(ns problems.141)


(defn __ [trump]
  (fn [cards]
    (let [mult (merge {(:suit (first cards)) 1} {trump 100})]
      (apply max-key #(* (inc (:rank %)) (get mult (:suit %) 0)) cards))))

(assert (= {:suit :club :rank 10}
           ((__ :club) [{:suit :spade :rank 2}
                        {:suit :club :rank 10}])))

(assert (= {:suit :heart :rank 8}
   ((__ :heart) [{:suit :heart :rank 6} {:suit :heart :rank 8}
                 {:suit :diamond :rank 10} {:suit :heart :rank 4}])))
