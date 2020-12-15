/**
 * Provides features to manipulate regular expressions in a more intuitive way.
 * <p>
 * There are <i>two ways</i> to specify the regular expression pattern to be
 * used, either by call Epitaph#Builder#pattern(String) and directly specifying
 * an arbitrary pattern as string, or by call
 * Epitaphz#Builder#pattern(RegexPattern) specifying it from a preset provided
 * by Epitaph based on egexPattern .
 * <p>
 * You can also call Epitaph#Builder#option(EnumSet) as optional and specify
 * options set based on RegexOption to be used when parsing with regular
 * expressions.
 * <p>
 * For regular expression parsing, the find() , lookingAt() and matches()
 * methods are provided. For strings matched in the regular expression parsing
 * process, you can use group() method to get the string within the range of the
 * currently matched index.
 *
 * <pre>
 * Specify the regex pattern as preset:
 * <code>
 * Epitaph epitaph = Epitaph.builder().pattern(RegexPattern.JAPANESE_ALPHABET).input("test").build();
 * epitaph.find();
 * epitaph.lookigAt();
 * epitaph.matches();
 * epitaph.group();
 * </code>
 * </pre>
 *
 * <pre>
 * Specify the specific regex pattern:
 * <code>
 * Epitaph epitaph = Epitaph.builder().pattern("[A-Za-z]+").input("test").build();
 * epitaph.find();
 * epitaph.lookigAt();
 * epitaph.matches();
 * epitaph.group();
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
package org.thinkit.common.regex;
