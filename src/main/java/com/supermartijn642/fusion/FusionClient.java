package com.supermartijn642.fusion;

import com.google.common.collect.ImmutableSet;
import com.supermartijn642.fusion.api.model.DefaultModelTypes;
import com.supermartijn642.fusion.api.model.FusionModelTypeRegistry;
import com.supermartijn642.fusion.api.predicate.FusionPredicateRegistry;
import com.supermartijn642.fusion.api.texture.DefaultTextureTypes;
import com.supermartijn642.fusion.api.texture.FusionTextureTypeRegistry;
import com.supermartijn642.fusion.predicate.*;
import com.supermartijn642.fusion.texture.FusionMetadataSection;
import net.minecraft.client.renderer.texture.SpriteLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;

/**
 * Created 26/04/2023 by SuperMartijn642
 */
public class FusionClient {

    public static void init(){
        // Register default texture types
        FusionTextureTypeRegistry.registerTextureType(new ResourceLocation("fusion", "vanilla"), DefaultTextureTypes.VANILLA);
        FusionTextureTypeRegistry.registerTextureType(new ResourceLocation("fusion", "connecting"), DefaultTextureTypes.CONNECTING);
        FusionTextureTypeRegistry.registerTextureType(new ResourceLocation("fusion", "scrolling"), DefaultTextureTypes.SCROLLING);
        // Register default model types
        FusionModelTypeRegistry.registerModelType(new ResourceLocation("fusion", "unknown"), DefaultModelTypes.UNKNOWN);
        FusionModelTypeRegistry.registerModelType(new ResourceLocation("fusion", "vanilla"), DefaultModelTypes.VANILLA);
        FusionModelTypeRegistry.registerModelType(new ResourceLocation("fusion", "connecting"), DefaultModelTypes.CONNECTING);
        // Register default connection predicates
        FusionPredicateRegistry.registerConnectionPredicate(new ResourceLocation("fusion", "and"), AndConnectionPredicate.SERIALIZER);
        FusionPredicateRegistry.registerConnectionPredicate(new ResourceLocation("fusion", "or"), OrConnectionPredicate.SERIALIZER);
        FusionPredicateRegistry.registerConnectionPredicate(new ResourceLocation("fusion", "not"), NotConnectionPredicate.SERIALIZER);
        FusionPredicateRegistry.registerConnectionPredicate(new ResourceLocation("fusion", "is_same_block"), IsSameBlockConnectionPredicate.SERIALIZER);
        FusionPredicateRegistry.registerConnectionPredicate(new ResourceLocation("fusion", "is_same_state"), IsSameStateConnectionPredicate.SERIALIZER);
        FusionPredicateRegistry.registerConnectionPredicate(new ResourceLocation("fusion", "match_block"), MatchBlockConnectionPredicate.SERIALIZER);

        // Add Fusion's metadata section
        SpriteLoader.DEFAULT_METADATA_SECTIONS = ImmutableSet.<MetadataSectionSerializer<?>>builder()
            .addAll(SpriteLoader.DEFAULT_METADATA_SECTIONS)
            .add(FusionMetadataSection.INSTANCE)
            .build();

        // Finalize registration

//        ClientLifecycleEvents.CLIENT_STARTED.register(client -> TextureTypeRegistryImpl.finalizeRegistration()); TODO
//        ClientLifecycleEvents.CLIENT_STARTED.register(client -> ModelTypeRegistryImpl.finalizeRegistration());
//        ClientLifecycleEvents.CLIENT_STARTED.register(client -> PredicateRegistryImpl.finalizeRegistration());
    }
}
