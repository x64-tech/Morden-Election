package com.x64tech.mordenelection.models;

import java.time.LocalDateTime;
import java.util.List;

public class ElectionModel {
    private String electionID;

    private String electionName;

    private String electionDic;

    private List<Candidates> candidates;

    private String electionBegin;

    private String electionEnd;

    public ElectionModel(String electionID, String electionName, String electionDic,
                         List<Candidates> candidates, String electionBegin,
                         String electionEnd) {
        this.electionID = electionID;
        this.electionName = electionName;
        this.electionDic = electionDic;
        this.candidates = candidates;
        this.electionBegin = electionBegin;
        this.electionEnd = electionEnd;
    }

    public String getElectionID() {
        return electionID;
    }

    public void setElectionID(String electionID) {
        this.electionID = electionID;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public String getElectionDic() {
        return electionDic;
    }

    public void setElectionDic(String electionDic) {
        this.electionDic = electionDic;
    }

    public List<Candidates> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidates> candidates) {
        this.candidates = candidates;
    }

    public String getElectionBegin() {
        return electionBegin;
    }

    public void setElectionBegin(String electionBegin) {
        this.electionBegin = electionBegin;
    }

    public String getElectionEnd() {
        return electionEnd;
    }

    public void setElectionEnd(String electionEnd) {
        this.electionEnd = electionEnd;
    }

    public static class Candidates {
        private final String name;
        private final String cryptoID;
        private final String userID;
        private final String electionID;

        public Candidates(String name, String cryptoID, String userID, String electionID) {
            this.name = name;
            this.cryptoID = cryptoID;
            this.userID = userID;
            this.electionID = electionID;
        }

        public String getName() {
            return name;
        }

        public String getCryptoID() {
            return cryptoID;
        }

        public String getUserID() {
            return userID;
        }

        public String getElectionID() {
            return electionID;
        }
    }
}
