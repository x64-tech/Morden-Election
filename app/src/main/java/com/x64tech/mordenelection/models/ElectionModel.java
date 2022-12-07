package com.x64tech.mordenelection.models;

import java.time.LocalDateTime;
import java.util.List;

public class ElectionModel {
    private String electionID;

    private String electionName;

    private String electionDic;

    private List<Candidates> candidates;

    private LocalDateTime electionBegin;

    private LocalDateTime electionEnd;

    public ElectionModel(String electionID, String electionName, String electionDic,
                         List<Candidates> candidates, LocalDateTime electionBegin,
                         LocalDateTime electionEnd) {
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

    public LocalDateTime getElectionBegin() {
        return electionBegin;
    }

    public void setElectionBegin(LocalDateTime electionBegin) {
        this.electionBegin = electionBegin;
    }

    public LocalDateTime getElectionEnd() {
        return electionEnd;
    }

    public void setElectionEnd(LocalDateTime electionEnd) {
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
