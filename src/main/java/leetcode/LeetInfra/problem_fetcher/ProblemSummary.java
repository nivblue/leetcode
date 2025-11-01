package leetcode.LeetInfra.problem_fetcher;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

/** Summary DTO for listing problems */
@Getter
@ToString
public class ProblemSummary {
    public String id;                 // questionFrontendId
    public String title;
    public String titleSlug;
    public String difficulty;
    public double acRate;             // percent (0-100)
    public boolean paidOnly;
    public List<String> topicTags;
}