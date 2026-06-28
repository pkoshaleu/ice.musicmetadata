package local.ice.service.catalog.command;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddVariationCommand.class, name = "addVariation"),
        @JsonSubTypes.Type(value = AddReleaseCommand.class, name = "addRelease"),
        @JsonSubTypes.Type(value = AddReleaseCreditCommand.class, name = "addReleaseCredit"),
        @JsonSubTypes.Type(value = AddTrackCommand.class, name = "addTrack")
})
public sealed interface CatalogCommand permits
        /* Artist */
        AddVariationCommand,
        /* Release */
        AddReleaseCommand, AddReleaseCreditCommand, AddTrackCommand {

            //~

}
