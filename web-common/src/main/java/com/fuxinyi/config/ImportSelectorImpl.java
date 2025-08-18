/**
 * @projectName web-parent
 * @package com.fuxinyi.config
 * @className com.fuxinyi.config.ImportSelectorImpl
 */
package com.fuxinyi.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ImportSelectorImpl
 *
 * @author william
 * @version 1.0
 * @description
 * @date 2025/8/18 12:05
 */
public class ImportSelectorImpl implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.fuxinyi.common.HttpClientConfig", "com.fuxinyi.common.Request",};
    }
}