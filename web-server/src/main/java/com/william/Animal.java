/**
 * @projectName web-parent
 * @package com.william
 * @className com.william.Animal
 */
package com.william;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Animal
 * @description
 * @author william
 * @date 2025/8/18 11:18
 * @version 1.0
 */
@Data
@Component
public class Animal {
    private String name;
}