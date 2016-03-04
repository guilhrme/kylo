/**
 * 
 */
package com.thinkbiganalytics.metadata.core.feed;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.thinkbiganalytics.metadata.api.dataset.Dataset;
import com.thinkbiganalytics.metadata.api.feed.Feed;
import com.thinkbiganalytics.metadata.api.feed.FeedData;
import com.thinkbiganalytics.metadata.api.feed.FeedDestination;
import com.thinkbiganalytics.metadata.api.feed.FeedSource;
import com.thinkbiganalytics.metadata.sla.api.ServiceLevelAgreement;

/**
 *
 * @author Sean Felten
 */
public class BaseFeed implements Feed {

    private ID Id;
    private String Name;
    private String Description;
    private Set<FeedSource> sources = new HashSet<>();
    private Set<FeedDestination> destinations = new HashSet<>();
    

    public BaseFeed(String name, String description) {
        this.Id = new FeedId();
        Name = name;
        Description = description;
    }

    public ID getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public Set<FeedSource> getSources() {
        return this.sources;
    }

    public Set<FeedDestination> getDestinations() {
        return destinations;
    }
    
    @Override
    public FeedDestination getDestination(Dataset.ID id) {
        for (FeedDestination dest : this.destinations) {
            if (dest.getDataset().getId().equals(id)) {
                return dest;
            }
        }
        
        return null;
    }

    public FeedSource addSource(Dataset ds) {
        return addSource(ds, null);
    }

    public FeedSource addSource(Dataset ds, ServiceLevelAgreement.ID agreemenetId) {
        Source src = new Source(ds, agreemenetId);
        this.sources.add(src);
        return src;
    }

    public FeedDestination addDestination(Dataset ds) {
        FeedDestination dest = new Destination(ds);
        this.destinations.add(dest);
        return dest;
    }
    
    private static class BaseId {
        private final UUID uuid;
        
        public BaseId() {
            this.uuid = UUID.randomUUID();
        }
        
        public BaseId(Serializable ser) {
            if (ser instanceof String) {
                this.uuid = UUID.fromString((String) ser);
            } else if (ser instanceof UUID) {
                this.uuid = (UUID) ser;
            } else {
                throw new IllegalArgumentException("Unknown ID value: " + ser);
            }
        }
        
        @Override
        public boolean equals(Object obj) {
            if (getClass().isAssignableFrom(obj.getClass())) {
                BaseId that = (BaseId) obj;
                return Objects.equals(this.uuid, that.uuid);
            } else {
                return false;
            }
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(getClass(), this.uuid);
        }
        
        @Override
        public String toString() {
            return this.uuid.toString();
        }
    }
    
    
    protected static class FeedId extends BaseId implements Feed.ID {
        public FeedId() {
            super();
        }

        public FeedId(Serializable ser) {
            super(ser);
        }
    }
    
    protected static class SourceId extends BaseId implements FeedSource.ID {
        public SourceId() {
            super();
        }

        public SourceId(Serializable ser) {
            super(ser);
        } 
    }
    
    protected static class DestinationId extends BaseId implements FeedDestination.ID {
        public DestinationId() {
            super();
        }

        public DestinationId(Serializable ser) {
            super(ser);
        } 
    }
    

    private abstract class Data implements FeedData {
        
        private Dataset dataset;
        
        public Data(Dataset ds) {
            this.dataset = ds;
        }
        
        @Override
        public Feed getFeed() {
            return BaseFeed.this;
        }

        @Override
        public Dataset getDataset() {
            return this.dataset;
        }
    }
    
    private class Source extends Data implements FeedSource {

        private SourceId id;
        private ServiceLevelAgreement.ID agreemenetId;
        
        public Source(Dataset ds, ServiceLevelAgreement.ID agreementId) {
            super(ds);
            this.id = new SourceId();
            this.agreemenetId = agreementId;
        }
 
        @Override
        public ID getId() {
            return this.id;
        }
        
        @Override
        public ServiceLevelAgreement.ID getAgreementId() {
            return this.agreemenetId;
        }
    }
    
    private class Destination extends Data implements FeedDestination {

        private DestinationId id;
        
        public Destination(Dataset ds) {
            super(ds);
            this.id = new DestinationId();
        }
        
        @Override
        public ID getId() {
            return this.id;
        }
    }

}
