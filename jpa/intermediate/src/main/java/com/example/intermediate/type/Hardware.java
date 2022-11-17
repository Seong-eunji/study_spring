package com.example.intermediate.type;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// 상속 관계가 아니다
// 각 필드만 개별적으로 사용되지 않고, 묶어서 한 번에 사용될 때, 사용할 때에 정확히 확인이 필요할 때 Embedded 방식을 사용한다.
// 만약 Embeddanl에 선언된 필드를 통째로 자주 사용할 때에도 Embadded 방식으로 설계한다.
// 필드 하나뿐만이 아니라 Hardware의 필드 전체를 다 가져와 사용할 때 주로 사용
@Embeddable // 모듈화
public class Hardware {
    @Column(name = "COMPUTER_RAM")
    private int computerRAM;
    @Column(name = "COMPUTER_SSD")
    private int computerSSD;
    @Column(name = "COMPUTER_GPU")
    private String computerGPU;
    private String computerProcessor;

    public void create(int computerRAM, int computerSSD, String computerGPU, String computerProcessor) {
        this.computerRAM = computerRAM;
        this.computerSSD = computerSSD;
        this.computerGPU = computerGPU;
        this.computerProcessor = computerProcessor;
    }
}
