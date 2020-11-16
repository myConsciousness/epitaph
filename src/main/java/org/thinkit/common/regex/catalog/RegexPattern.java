/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.common.regex.catalog;

import org.thinkit.api.catalog.BiCatalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The catalog that manages regex patterns.
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum RegexPattern implements BiCatalog<RegexPattern, String> {

    /**
     * Email address
     */
    EMAIL_ADDRESS(0, "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"),

    /**
     * Domain name
     */
    DOMAIN_NAME(1, "^[a-zA-Z0-9][a-zA-Z0-9-]{1,61}[a-zA-Z0-9]\\.[a-zA-Z]{2,}$"),

    /**
     * Web URL
     */
    WEB_URL(2, "^(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$"),

    /**
     * User name
     * <p>
     * Specify the minimum and maximum length of the username as follows.
     *
     * <pre>
     * <code>
     * String.format(RegexPattern.USER_NAME.getTag(), "3", "15");
     * </code>
     * </pre>
     */
    USER_NAME(3, "^[a-zA-Z0-9_\\-.]{%s,%s}$");

    /**
     * The code
     */
    @Getter
    private final int code;

    /**
     * The tag
     */
    @Getter
    private final String tag;
}
