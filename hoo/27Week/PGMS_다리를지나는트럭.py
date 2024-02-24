def solution(bridge_length, weight, truck_weights):
    answer = 0
    bridge = [0] * bridge_length
    t_on_b = 0

    while bridge:
        answer += 1
        t_on_b -= bridge.pop(0)
        
        if truck_weights:
            if (t_on_b + truck_weights[0]) <= weight:
                t = truck_weights.pop(0)
                t_on_b += t
                bridge.append(t)
            else:
                bridge.append(0)
        
    return answer