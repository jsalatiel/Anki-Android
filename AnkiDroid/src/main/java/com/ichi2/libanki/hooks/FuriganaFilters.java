/***************************************************************************************
 * Copyright (c) 2012 Kostas Spyropoulos <inigo.aldana@gmail.com>                       *
 *                                                                                      *
 * This program is free software; you can redistribute it and/or modify it under        *
 * the terms of the GNU General Public License as published by the Free Software        *
 * Foundation; either version 3 of the License, or (at your option) any later           *
 * version.                                                                             *
 *                                                                                      *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY      *
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A      *
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.             *
 *                                                                                      *
 * You should have received a copy of the GNU General Public License along with         *
 * this program.  If not, see <http://www.gnu.org/licenses/>.                           *
 ****************************************************************************************/

package com.ichi2.libanki.hooks;

import com.ichi2.compat.CompatHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FuriganaFilters {
    private static final Pattern r = Pattern.compile(" ?([^ >]+?)\\[(.+?)\\]");

    private static final String RUBY = "<ruby><rb>$1</rb><rt>$2</rt></ruby>";

    public void install(Hooks h) {
        h.addHook("mungeQA", new Furigana());
    }

    public class Furigana extends Hook {
        @Override
        public Object runFilter(Object arg, Object... args) {
            Matcher m = r.matcher((String) arg);
            StringBuffer sb = new StringBuffer();
            while (m.find()) {
                m.appendReplacement(sb, RUBY);
            }
            m.appendTail(sb);
            return sb.toString();
        }
    }

}
