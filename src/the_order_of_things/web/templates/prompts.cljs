(ns the-order-of-things.web.templates.prompts)

(def default-wait-ms 300)

(defn debounce [f wait-ms]
  (let [-timer (atom nil)
        clear-timeout #(js/clearTimeout %)
        set-timeout #(js/setTimeout % wait-ms)]
    (fn [& args]
      (when-let [timer @-timer]
        (clear-timeout timer))
      (reset! -timer (set-timeout #(apply f args))))))

(defn input
  [-value
   & {:keys [on-submit on-change
             placeholder wait
             options-list input-type
             props]
      :or {wait default-wait-ms}}]
  (let [oninput #(reset! -value (-> % .-target .-value))
        onkeydown
        (debounce
         #(cond
            (and on-submit (= 13 (.-which %))) (on-submit @-value)
            on-change (on-change @-value))
         wait)]
    [:input.input
     (cond->
      {:type input-type
       :name input-type
       :value @-value
       :oninput oninput}
       placeholder              (assoc :placeholder placeholder)
       (or on-submit on-change) (assoc :onkeydown onkeydown)
       options-list             (assoc :list options-list)
       props                    (merge props))]))

(defn text [-value & args]
  (apply input -value :input-type "text" args))

(defn search [-value & args]
  (apply input -value :input-type "search" args))

(defn number [-value & args]
  (apply input -value :input-type "number" args))
