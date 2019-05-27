! function(e, t) { "object" == typeof exports && "object" == typeof module ? module.exports = t() : "function" == typeof define && define.amd ? define([], t) : "object" == typeof exports ? exports.template = t() : e.template = t() }(this, function() { return function(e) {
        function t(r) { if (n[r]) return n[r].exports; var i = n[r] = { i: r, l: !1, exports: {} }; return e[r].call(i.exports, i, i.exports, t), i.l = !0, i.exports } var n = {}; return t.m = e, t.c = n, t.d = function(e, n, r) { t.o(e, n) || Object.defineProperty(e, n, { configurable: !1, enumerable: !0, get: r }) }, t.n = function(e) { var n = e && e.__esModule ? function() { return e.default } : function() { return e }; return t.d(n, "a", n), n }, t.o = function(e, t) { return Object.prototype.hasOwnProperty.call(e, t) }, t.p = "", t(t.s = 6) }([function(e, t, n) {
        (function(t) { e.exports = !1; try { e.exports = "[object process]" === Object.prototype.toString.call(t.process) } catch (e) {} }).call(t, n(4)) }, function(e, t, n) { "use strict"; var r = n(8),
            i = n(3),
            o = n(23),
            s = function(e, t) { t.onerror(e, t); var n = function() { return "{Template Error}" }; return n.mappings = [], n.sourcesContent = [], n },
            a = function e(t) { var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {}; "string" != typeof t ? n = t : n.source = t, n = i.$extend(n), t = n.source, !0 === n.debug && (n.cache = !1, n.minimize = !1, n.compileDebug = !0), n.compileDebug && (n.minimize = !1), n.filename && (n.filename = n.resolveFilename(n.filename, n)); var a = n.filename,
                    c = n.cache,
                    u = n.caches; if (c && a) { var p = u.get(a); if (p) return p } if (!t) try { t = n.loader(a, n), n.source = t } catch (e) { var f = new o({ name: "CompileError", path: a, message: "template not found: " + e.message, stack: e.stack }); if (n.bail) throw f; return s(f, n) }
                var l = void 0,
                    h = new r(n); try { l = h.build() } catch (f) { if (f = new o(f), n.bail) throw f; return s(f, n) } var m = function(t, r) { try { return l(t, r) } catch (i) { if (!n.compileDebug) return n.cache = !1, n.compileDebug = !0, e(n)(t, r); if (i = new o(i), n.bail) throw i; return s(i, n)() } }; return m.mappings = l.mappings, m.sourcesContent = l.sourcesContent, m.toString = function() { return l.toString() }, c && a && u.set(a, m), m };
        a.Compiler = r, e.exports = a }, function(e, t) { Object.defineProperty(t, "__esModule", { value: !0 }), t.default = /((['"])(?:(?!\2|\\).|\\(?:\r\n|[\s\S]))*(\2)?|`(?:[^`\\$]|\\[\s\S]|\$(?!\{)|\$\{(?:[^{}]|\{[^}]*\}?)*\}?)*(`)?)|(\/\/.*)|(\/\*(?:[^*]|\*(?!\/))*(\*\/)?)|(\/(?!\*)(?:\[(?:(?![\]\\]).|\\.)*\]|(?![\/\]\\]).|\\.)+\/(?:(?!\s*(?:\b|[\u0080-\uFFFF$\\'"~({]|[+\-!](?!=)|\.?\d))|[gmiyu]{1,5}\b(?![\u0080-\uFFFF$\\]|\s*(?:[+\-*%&|^<>!=?({]|\/(?![\/*])))))|(0[xX][\da-fA-F]+|0[oO][0-7]+|0[bB][01]+|(?:\d*\.\d+|\d+\.?)(?:[eE][+-]?\d+)?)|((?!\d)(?:(?!\s)[$\w\u0080-\uFFFF]|\\u[\da-fA-F]{4}|\\u\{[\da-fA-F]+\})+)|(--|\+\+|&&|\|\||=>|\.{3}|(?:[+\-\/%&|^]|\*{1,2}|<{1,2}|>{1,3}|!=?|={1,2})=?|[?~.,:;[\](){}])|(\s+)|(^$|[\s\S])/g, t.matchToToken = function(e) { var t = { type: "invalid", value: e[0] }; return e[1] ? (t.type = "string", t.closed = !(!e[3] && !e[4])) : e[5] ? t.type = "comment" : e[6] ? (t.type = "comment", t.closed = !!e[7]) : e[8] ? t.type = "regex" : e[9] ? t.type = "number" : e[10] ? t.type = "name" : e[11] ? t.type = "punctuator" : e[12] && (t.type = "whitespace"), t } }, function(e, t, n) { "use strict";

        function r() { this.$extend = function(e) { return e = e || {}, s(e, e instanceof r ? e : this) } } var i = n(0),
            o = n(12),
            s = n(13),
            a = n(14),
            c = n(15),
            u = n(16),
            p = n(17),
            f = n(18),
            l = n(19),
            h = n(20),
            m = n(22),
            v = { source: null, filename: null, rules: [l, f], escape: !0, debug: !!i && "production" !== process.env.NODE_ENV, bail: !0, cache: !0, minimize: !0, compileDebug: !1, resolveFilename: m, include: a, htmlMinifier: h, htmlMinifierOptions: { collapseWhitespace: !0, minifyCSS: !0, minifyJS: !0, ignoreCustomFragments: [] }, onerror: c, loader: p, caches: u, root: "/", extname: ".art", ignore: [], imports: o };
        r.prototype = v, e.exports = new r }, function(e, t) { var n;
        n = function() { return this }(); try { n = n || Function("return this")() || (0, eval)("this") } catch (e) { "object" == typeof window && (n = window) } e.exports = n }, function(e, t) {}, function(e, t, n) { "use strict"; var r = n(7),
            i = n(1),
            o = n(24),
            s = function(e, t) { return t instanceof Object ? r({ filename: e }, t) : i({ filename: e, source: t }) };
        s.render = r, s.compile = i, s.defaults = o, e.exports = s }, function(e, t, n) { "use strict"; var r = n(1),
            i = function(e, t, n) { return r(e, n)(t) };
        e.exports = i }, function(e, t, n) { "use strict";

        function r(e, t) { if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function") } var i = n(9),
            o = n(11),
            s = "$data",
            a = "$imports",
            c = "print",
            u = "include",
            p = "extend",
            f = "block",
            l = "$$out",
            h = "$$line",
            m = "$$blocks",
            v = "$$slice",
            d = "$$from",
            g = "$$options",
            y = function(e, t) { return e.hasOwnProperty(t) },
            b = JSON.stringify,
            x = function() {
                function e(t) { var n, i, y = this;
                    r(this, e); var b = t.source,
                        x = t.minimize,
                        w = t.htmlMinifier; if (this.options = t, this.stacks = [], this.context = [], this.scripts = [], this.CONTEXT_MAP = {}, this.ignore = [s, a, g].concat(t.ignore), this.internal = (n = {}, n[l] = "''", n[h] = "[0,0]", n[m] = "arguments[1]||{}", n[d] = "null", n[c] = "function(){var s=''.concat.apply('',arguments);" + l + "+=s;return s}", n[u] = "function(src,data){var s=" + g + ".include(src,data||" + s + ",arguments[2]||" + m + "," + g + ");" + l + "+=s;return s}", n[p] = "function(from){" + d + "=from}", n[v] = "function(c,p,s){p=" + l + ";" + l + "='';c();s=" + l + ";" + l + "=p+s;return s}", n[f] = "function(){var a=arguments,s;if(typeof a[0]==='function'){return " + v + "(a[0])}else if(" + d + "){" + m + "[a[0]]=" + v + "(a[1])}else{s=" + m + "[a[0]];if(typeof s==='string'){" + l + "+=s}else{s=" + v + "(a[1])}return s}}", n), this.dependencies = (i = {}, i[c] = [l], i[u] = [l, g, s, m], i[p] = [d, u], i[f] = [v, d, l, m], i), this.importContext(l), t.compileDebug && this.importContext(h), x) try { b = w(b, t) } catch (e) {} this.source = b, this.getTplTokens(b, t.rules, this).forEach(function(e) { e.type === o.TYPE_STRING ? y.parseString(e) : y.parseExpression(e) }) } return e.prototype.getTplTokens = function() { return o.apply(void 0, arguments) }, e.prototype.getEsTokens = function(e) { return i(e) }, e.prototype.getVariables = function(e) { var t = !1; return e.filter(function(e) { return "whitespace" !== e.type && "comment" !== e.type }).filter(function(e) { return "name" === e.type && !t || (t = "punctuator" === e.type && "." === e.value, !1) }).map(function(e) { return e.value }) }, e.prototype.importContext = function(e) { var t = this,
                        n = "",
                        r = this.internal,
                        i = this.dependencies,
                        o = this.ignore,
                        c = this.context,
                        u = this.options,
                        p = u.imports,
                        f = this.CONTEXT_MAP;
                    y(f, e) || -1 !== o.indexOf(e) || (y(r, e) ? (n = r[e], y(i, e) && i[e].forEach(function(e) { return t.importContext(e) })) : n = "$escape" === e || "$each" === e || y(p, e) ? a + "." + e : s + "." + e, f[e] = n, c.push({ name: e, value: n })) }, e.prototype.parseString = function(e) { var t = e.value; if (t) { var n = l + "+=" + b(t);
                        this.scripts.push({ source: t, tplToken: e, code: n }) } }, e.prototype.parseExpression = function(e) { var t = this,
                        n = e.value,
                        r = e.script,
                        i = r.output,
                        s = this.options.escape,
                        a = r.code;
                    i && (a = !1 === s || i === o.TYPE_RAW ? l + "+=" + r.code : l + "+=$escape(" + r.code + ")"); var c = this.getEsTokens(a);
                    this.getVariables(c).forEach(function(e) { return t.importContext(e) }), this.scripts.push({ source: n, tplToken: e, code: a }) }, e.prototype.checkExpression = function(e) { for (var t = [
                            [/^\s*}[\w\W]*?{?[\s;]*$/, ""],
                            [/(^[\w\W]*?\([\w\W]*?(?:=>|\([\w\W]*?\))\s*{[\s;]*$)/, "$1})"],
                            [/(^[\w\W]*?\([\w\W]*?\)\s*{[\s;]*$)/, "$1}"]
                        ], n = 0; n < t.length;) { if (t[n][0].test(e)) { var r;
                            e = (r = e).replace.apply(r, t[n]); break } n++ } try { return new Function(e), !0 } catch (e) { return !1 } }, e.prototype.build = function() { var e = this.options,
                        t = this.context,
                        n = this.scripts,
                        r = this.stacks,
                        i = this.source,
                        c = e.filename,
                        f = e.imports,
                        v = [],
                        x = y(this.CONTEXT_MAP, p),
                        w = 0,
                        E = function(e, t) { var n = t.line,
                                i = t.start,
                                o = { generated: { line: r.length + w + 1, column: 1 }, original: { line: n + 1, column: i + 1 } }; return w += e.split(/\n/).length - 1, o },
                        k = function(e) { return e.replace(/^[\t ]+|[\t ]$/g, "") };
                    r.push("function(" + s + "){"), r.push("'use strict'"), r.push(s + "=" + s + "||{}"), r.push("var " + t.map(function(e) { return e.name + "=" + e.value }).join(",")), e.compileDebug ? (r.push("try{"), n.forEach(function(e) { e.tplToken.type === o.TYPE_EXPRESSION && r.push(h + "=[" + [e.tplToken.line, e.tplToken.start].join(",") + "]"), v.push(E(e.code, e.tplToken)), r.push(k(e.code)) }), r.push("}catch(error){"), r.push("throw {" + ["name:'RuntimeError'", "path:" + b(c), "message:error.message", "line:" + h + "[0]+1", "column:" + h + "[1]+1", "source:" + b(i), "stack:error.stack"].join(",") + "}"), r.push("}")) : n.forEach(function(e) { v.push(E(e.code, e.tplToken)), r.push(k(e.code)) }), x && (r.push(l + "=''"), r.push(u + "(" + d + "," + s + "," + m + ")")), r.push("return " + l), r.push("}"); var T = r.join("\n"); try { var O = new Function(a, g, "return " + T)(f, e); return O.mappings = v, O.sourcesContent = [i], O } catch (e) { for (var $ = 0, j = 0, S = 0, _ = void 0; $ < n.length;) { var C = n[$]; if (!this.checkExpression(C.code)) { j = C.tplToken.line, S = C.tplToken.start, _ = C.code; break } $++ } throw { name: "CompileError", path: c, message: e.message, line: j + 1, column: S + 1, source: i, generated: _, stack: e.stack } } }, e }();
        x.CONSTS = { DATA: s, IMPORTS: a, PRINT: c, INCLUDE: u, EXTEND: p, BLOCK: f, OPTIONS: g, OUT: l, LINE: h, BLOCKS: m, SLICE: v, FROM: d, ESCAPE: "$escape", EACH: "$each" }, e.exports = x }, function(e, t, n) { "use strict"; var r = n(10),
            i = n(2).default,
            o = n(2).matchToToken,
            s = function(e) { return e.match(i).map(function(e) { return i.lastIndex = 0, o(i.exec(e)) }).map(function(e) { return "name" === e.type && r(e.value) && (e.type = "keyword"), e }) };
        e.exports = s }, function(e, t, n) { "use strict"; var r = { abstract: !0, await: !0, boolean: !0, break: !0, byte: !0, case: !0, catch: !0, char: !0, class: !0, const: !0, continue: !0, debugger: !0, default: !0, delete: !0, do: !0, double: !0, else: !0, enum: !0, export: !0, extends: !0, false: !0, final: !0, finally: !0, float: !0, for: !0, function: !0, goto: !0, if: !0, implements: !0, import: !0, in: !0, instanceof: !0, int: !0, interface: !0, let: !0, long: !0, native: !0, new: !0, null: !0, package: !0, private: !0, protected: !0, public: !0, return: !0, short: !0, static: !0, super: !0, switch: !0, synchronized: !0, this: !0, throw: !0, transient: !0, true: !0, try: !0, typeof: !0, var: !0, void: !0, volatile: !0, while: !0, with: !0, yield: !0 };
        e.exports = function(e) { return r.hasOwnProperty(e) } }, function(e, t, n) { "use strict";

        function r(e, t, n, r) { var i = new String(e); return i.line = t, i.start = n, i.end = r, i } var i = function(e, t) { for (var n = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {}, i = [{ type: "string", value: e, line: 0, start: 0, end: e.length }], o = 0; o < t.length; o++) ! function(e) { for (var t = e.test.ignoreCase ? "ig" : "g", o = e.test.source + "|^$|[\\w\\W]", s = new RegExp(o, t), a = 0; a < i.length; a++)
                    if ("string" === i[a].type) { for (var c = i[a].line, u = i[a].start, p = i[a].end, f = i[a].value.match(s), l = [], h = 0; h < f.length; h++) { var m = f[h];
                            e.test.lastIndex = 0; var v = e.test.exec(m),
                                d = v ? "expression" : "string",
                                g = l[l.length - 1],
                                y = g || i[a],
                                b = y.value;
                            u = y.line === c ? g ? g.end : u : b.length - b.lastIndexOf("\n") - 1, p = u + m.length; var x = { type: d, value: m, line: c, start: u, end: p }; if ("string" === d) g && "string" === g.type ? (g.value += m, g.end += m.length) : l.push(x);
                            else { v[0] = new r(v[0], c, u, p); var w = e.use.apply(n, v);
                                x.script = w, l.push(x) } c += m.split(/\n/).length - 1 } i.splice.apply(i, [a, 1].concat(l)), a += l.length - 1 } }(t[o]); return i };
        i.TYPE_STRING = "string", i.TYPE_EXPRESSION = "expression", i.TYPE_RAW = "raw", i.TYPE_ESCAPE = "escape", e.exports = i }, function(e, t, n) { "use strict";
        (function(t) {
            function r(e) { return "string" != typeof e && (e = void 0 === e || null === e ? "" : "function" == typeof e ? r(e.call(e)) : JSON.stringify(e)), e }

            function i(e) { var t = "" + e,
                    n = a.exec(t); if (!n) return e; var r = "",
                    i = void 0,
                    o = void 0,
                    s = void 0; for (i = n.index, o = 0; i < t.length; i++) { switch (t.charCodeAt(i)) {
                        case 34:
                            s = "&#34;"; break;
                        case 38:
                            s = "&#38;"; break;
                        case 39:
                            s = "&#39;"; break;
                        case 60:
                            s = "&#60;"; break;
                        case 62:
                            s = "&#62;"; break;
                        default:
                            continue } o !== i && (r += t.substring(o, i)), o = i + 1, r += s } return o !== i ? r + t.substring(o, i) : r } var o = n(0),
                s = Object.create(o ? t : window),
                a = /["&'<>]/;
            s.$escape = function(e) { return i(r(e)) }, s.$each = function(e, t) { if (Array.isArray(e))
                    for (var n = 0, r = e.length; n < r; n++) t(e[n], n);
                else
                    for (var i in e) t(e[i], i) }, e.exports = s }).call(t, n(4)) }, function(e, t, n) { "use strict"; var r = Object.prototype.toString,
            i = function(e) { return null === e ? "Null" : r.call(e).slice(8, -1) },
            o = function e(t, n) { var r = void 0,
                    o = i(t); if ("Object" === o ? r = Object.create(n || {}) : "Array" === o && (r = [].concat(n || [])), r) { for (var s in t) t.hasOwnProperty(s) && (r[s] = e(t[s], r[s])); return r } return t };
        e.exports = o }, function(e, t, n) { "use strict"; var r = function(e, t, r, i) { var o = n(1); return i = i.$extend({ filename: i.resolveFilename(e, i), bail: !0, source: null }), o(i)(t, r) };
        e.exports = r }, function(e, t, n) { "use strict"; var r = function(e) {};
        e.exports = r }, function(e, t, n) { "use strict"; var r = { __data: Object.create(null), set: function(e, t) { this.__data[e] = t }, get: function(e) { return this.__data[e] }, reset: function() { this.__data = {} } };
        e.exports = r }, function(e, t, n) { "use strict"; var r = n(0),
            i = function(e) { if (r) return n(5).readFileSync(e, "utf8"); var t = document.getElementById(e); return t.value || t.innerHTML };
        e.exports = i }, function(e, t, n) { "use strict"; var r = { test: /{{([@#]?)[ \t]*(\/?)([\w\W]*?)[ \t]*}}/, use: function(e, t, n, i) { var o = this,
                    s = (o.options, o.getEsTokens(i)),
                    a = s.map(function(e) { return e.value }),
                    c = {},
                    u = void 0,
                    p = !!t && "raw",
                    f = n + a.shift(); switch (f) {
                    case "set":
                        i = "var " + a.join("").trim(); break;
                    case "if":
                        i = "if(" + a.join("").trim() + "){"; break;
                    case "else":
                        var l = a.indexOf("if");~l ? (a.splice(0, l + 1), i = "}else if(" + a.join("").trim() + "){") : i = "}else{"; break;
                    case "/if":
                        i = "}"; break;
                    case "each":
                        u = r._split(s), u.shift(), "as" === u[1] && u.splice(1, 1), i = "$each(" + (u[0] || "$data") + ",function(" + (u[1] || "$value") + "," + (u[2] || "$index") + "){"; break;
                    case "/each":
                        i = "})"; break;
                    case "block":
                        u = r._split(s), u.shift(), i = "block(" + u.join(",").trim() + ",function(){"; break;
                    case "/block":
                        i = "})"; break;
                    case "echo":
                        f = "print";
                    case "print":
                    case "include":
                    case "extend":
                        if (0 !== a.join("").trim().indexOf("(")) { u = r._split(s), u.shift(), i = f + "(" + u.join(",") + ")"; break }
                    default:
                        if (~a.indexOf("|")) { var h = s.reduce(function(e, t) { var n = t.value,
                                    r = t.type; return "|" === n ? e.push([]) : "whitespace" !== r && "comment" !== r && (e.length || e.push([]), ":" === n && 1 === e[e.length - 1].length || e[e.length - 1].push(t)), e }, []).map(function(e) { return r._split(e) });
                            i = h.reduce(function(e, t) { var n = t.shift(); return t.unshift(e), "$imports." + n + "(" + t.join(",") + ")" }, h.shift().join(" ").trim()) } p = p || "escape" } return c.code = i, c.output = p, c }, _split: function(e) { e = e.filter(function(e) { var t = e.type; return "whitespace" !== t && "comment" !== t }); for (var t = 0, n = e.shift(), r = /\]|\)/, i = [
                        [n]
                    ]; t < e.length;) { var o = e[t]; "punctuator" === o.type || "punctuator" === n.type && !r.test(n.value) ? i[i.length - 1].push(o) : i.push([o]), n = o, t++ } return i.map(function(e) { return e.map(function(e) { return e.value }).join("") }) } };
        e.exports = r }, function(e, t, n) { "use strict"; var r = { test: /<%(#?)((?:==|=#|[=-])?)[ \t]*([\w\W]*?)[ \t]*(-?)%>/, use: function(e, t, n, r) { return n = { "-": "raw", "=": "escape", "": !1, "==": "raw", "=#": "raw" } [n], t && (r = "/*" + r + "*/", n = !1), { code: r, output: n } } };
        e.exports = r }, function(e, t, n) { "use strict"; var r = n(0),
            i = function(e, t) { if (r) { var i, o = n(21).minify,
                        s = t.htmlMinifierOptions,
                        a = t.rules.map(function(e) { return e.test });
                    (i = s.ignoreCustomFragments).push.apply(i, a), e = o(e, s) } return e };
        e.exports = i }, function(e, t) {! function(e) { e.noop = function() {} }("object" == typeof e && "object" == typeof e.exports ? e.exports : window) }, function(e, t, n) { "use strict"; var r = n(0),
            i = /^\.+\//,
            o = function(e, t) { if (r) { var o = n(5),
                        s = t.root,
                        a = t.extname; if (i.test(e)) { var c = t.filename,
                            u = !c || e === c,
                            p = u ? s : o.dirname(c);
                        e = o.resolve(p, e) } else e = o.resolve(s, e);
                    o.extname(e) || (e += a) } return e };
        e.exports = o }, function(e, t, n) { "use strict";

        function r(e, t) { if (!(e instanceof t)) throw new TypeError("Cannot call a class as a function") }

        function i(e, t) { if (!e) throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); return !t || "object" != typeof t && "function" != typeof t ? e : t }

        function o(e, t) { if ("function" != typeof t && null !== t) throw new TypeError("Super expression must either be null or a function, not " + typeof t);
            e.prototype = Object.create(t && t.prototype, { constructor: { value: e, enumerable: !1, writable: !0, configurable: !0 } }), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t) }

        function s(e) { var t = e.name,
                n = e.source,
                r = e.path,
                i = e.line,
                o = e.column,
                s = e.generated,
                a = e.message; if (!n) return a; var c = n.split(/\n/),
                u = Math.max(i - 3, 0),
                p = Math.min(c.length, i + 3),
                f = c.slice(u, p).map(function(e, t) { var n = t + u + 1; return (n === i ? " >> " : "    ") + n + "| " + e }).join("\n"); return (r || "anonymous") + ":" + i + ":" + o + "\n" + f + "\n\n" + t + ": " + a + (s ? "\n   generated: " + s : "") } var a = function(e) {
            function t(n) { r(this, t); var o = i(this, e.call(this, n.message)); return o.name = "TemplateError", o.message = s(n), Error.captureStackTrace && Error.captureStackTrace(o, o.constructor), o } return o(t, e), t }(Error);
        e.exports = a }, function(e, t, n) { "use strict";
        e.exports = n(3) }]) });