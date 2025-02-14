package de.gecko.medicats.ops.sgml;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import de.gecko.medicats.Pair;
import org.w3c.dom.Element;

import de.gecko.medicats.ops.OpsNode;

public class SgmlOpsNode extends OpsNode {

    public static SgmlOpsNode createNode(SgmlOpsNode parent,
                                         Element element,
                                         String label,
                                         String code,
                                         OpsNodeType nodeType,
                                         List<String> inclusionCodes,
                                         List<String> exclusionCodes,
                                         List<String> inclusionStrings,
                                         List<String> exclusionStrings,
                                         List<String> hints) {
        SgmlOpsNode node = new SgmlOpsNode(Objects.requireNonNull(parent, "parent"),
                Objects.requireNonNull(element, "element"),
                Objects.requireNonNull(label, "label"),
                Objects.requireNonNull(code, "code"),
                Objects.requireNonNull(nodeType, "nodeType"),
                Objects.requireNonNull(inclusionCodes, "inclusionCodes"),
                Objects.requireNonNull(exclusionCodes, "exclusionCodes"),
                Objects.requireNonNull(inclusionStrings, "inclusionStrings"),
                Objects.requireNonNull(exclusionStrings, "exclusionStrings"),
                Objects.requireNonNull(hints, "hints"));

        if (code.isEmpty())
            throw new IllegalArgumentException("code is empty");

        node.addToParent();
        return node;
    }

    public static SgmlOpsNode createNode(SgmlOpsNode parent,
                                         Element element,
                                         String label,
                                         String code,
                                         OpsNodeType nodeType,
                                         Pair<List<String>, List<String>> inclusions,
                                         Pair<List<String>, List<String>> exclusions,
                                         List<String> hints) {
        return createNode(parent,
                element,
                label,
                code,
                nodeType,
                inclusions.getFirst(),
                exclusions.getFirst(),
                inclusions.getSecond(),
                exclusions.getSecond(),
                hints);
    }

    private final Element element;
    private final String label;
    private final String code;
    private final OpsNodeType nodeType;
    private final List<String> inclusionCodes;
    private final List<String> exclusionCodes;

    private final List<String> inclusionStrings;
    private final List<String> exclusionStrings;

    private final List<String> hints;

    protected SgmlOpsNode(OpsNode parent, Element element, String label, String code, OpsNodeType nodeType,
                          List<String> inclusionCodes, List<String> exclusionCodes, List<String> inclusionStrings, List<String> exclusionStrings, List<String> hints) {
        super(parent);

        this.element = element;
        this.label = label;
        this.code = code;
        this.nodeType = nodeType;
        this.inclusionCodes = inclusionCodes;
        this.exclusionCodes = exclusionCodes;
        this.inclusionStrings = inclusionStrings;
        this.exclusionStrings = exclusionStrings;
        this.hints = hints;
    }

    public Element getSgmlElement() {
        return element;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public OpsNodeType getNodeType() {
        return nodeType;
    }

    @SuppressWarnings("unused")
    public List<String> getInclusionCodes() {
        return inclusionCodes;
    }

    @SuppressWarnings("unused")
    public List<String> getExclusionCodes() {
        return exclusionCodes;
    }

    public List<String> getInclusionStrings() {
        return inclusionStrings;
    }

    public List<String> getExclusionStrings() {
        return exclusionStrings;
    }

    public List<String> getHints() {
        return hints;
    }

    @Override
    public Stream<OpsNode> getInclusionsImpl(Function<String, List<OpsNode>> byCode) {
        if (inclusionCodes == null || inclusionCodes.isEmpty())
            return Stream.empty();

        return inclusionCodes.stream().map(byCode).flatMap(Collection::stream).distinct();
    }

    @Override
    public Stream<OpsNode> getExclusionsImpl(Function<String, List<OpsNode>> byCode) {
        if (exclusionCodes == null || exclusionCodes.isEmpty())
            return Stream.empty();

        return exclusionCodes.stream().map(byCode).flatMap(Collection::stream).distinct();
    }

    public boolean isAmtl() {
        return "J".equals(getSgmlElement().getAttribute("AMTL"));
    }
}
