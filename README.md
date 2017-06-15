#TestsForExtJSApp

<h3>Run tests</h3>
<p>for Windows <code>gradlew clean test</code></p>
<p>for Unix <code>./gradlew clean test</code></p>

<h3>Test results</h3>
In root folder of project ./build/reports/tests/index.html


<h3>Bugs</h3>
<ol>
<li>Grid have default ascending sorting instead of descending</li>
<li>When adding new letter, you can choose letter from drop down and add print additional space, after clicking button ADD empty row will be added</li>
<li>When adding new letter, print letter(don't choose from dropdown),  click button ADD, letter added to tree/grid after that button ADD disappear in tree/grid</li>
<li>Letters that was add to tree, appears in grid</li>
<li>When drop-down multiply lines from grid to tree all letters instead of first will have wrong icon</li>
</ol>

<h3>Test cases</h3>
<ol>
<li>adaptive page layout (no automation, over-complex)</li>
<li>sorting tree</li>
<li>sorting grid</li>
<li>english alphabet</li>
<li>english letters</li>
<li>disable add button if nothing to add</li>
<li>drop-down tree -> grid</li>
<li>drop-down grid -> tree</li>
<li>delete from tree</li>
<li>delete from grid</li>
<li>add to grid(by selecting)</li>
<li>add to grid(by typing)</li>
<li>add to tree(by selecting)</li>
<li>add to tree(by typing)</li>
<li>multi-select work with grid</li>
<li>add window validation</li>
</ol>